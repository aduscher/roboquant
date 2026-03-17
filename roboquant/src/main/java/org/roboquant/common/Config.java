/*
 * Copyright 2020-2026 Neural Layer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.roboquant.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * The configuration for roboquant that contains access to environment properties and has several global properties
 * that can be set:
 * - The random number generator
 */
public final class Config {

    private static final Logger logger = LoggerFactory.getLogger(org.robok.common.Config.class);

    public static final org.roboquant.common.Config INSTANCE = new Config();

    private static final Map<String, String> properties = new HashMap<>();
    private static final int ONE_MB = 1024 * 1024;
    private static final long DEFAULT_SEED = 42L;

    /**
     * Used to handle Double imprecision.
     */
    public static final double EPS = 1e-10;

    private static EnvInfo info;
    private static Path home;
    private static Random random = new Random(DEFAULT_SEED);

    private Config() {  }

    /**
     * Information about the build and environment.
     */
    public static class EnvInfo {
        private final String jvm;
        private final String os;
        private final long memory;
        private final int cores;
        private final String version;
        private final String build;

        public EnvInfo(String jvm, String os, long memory, int cores, String version, String build) {
            this.jvm = jvm;
            this.os = os;
            this.memory = memory;
            this.cores = cores;
            this.version = version;
            this.build = build;
        }

        public String getJvm() {
            return jvm;
        }

        public String getOs() {
            return os;
        }

        public long getMemory() {
            return memory;
        }

        public int getCores() {
            return cores;
        }

        public String getVersion() {
            return version;
        }

        public String getBuild() {
            return build;
        }
    }

    /**
     * Get the environment info. Lazy initialized.
     */
    public static synchronized EnvInfo getInfo() throws ConfigurationException {
        if (info == null) {
            Properties prop = new Properties();
            InputStream stream = org.robok.common.Config.class.getResourceAsStream("/roboquant.properties");
            if (stream == null) {
                throw new ConfigurationException("couldn't load roboquant.properties from class path");
            }

            try {
                prop.load(stream);
            } catch (Exception e) {
                throw new ConfigurationException("Failed to load roboquant.properties: " + e.getMessage());
            } finally {
                try {
                    stream.close();
                } catch (Exception e) {
                    // ignore
                }
            }

            info = new EnvInfo(
                    System.getProperty("java.vm.name") + " " + System.getProperty("java.version"),
                    System.getProperty("os.name") + " " + System.getProperty("os.version"),
                    Runtime.getRuntime().maxMemory() / ONE_MB,
                    Runtime.getRuntime().availableProcessors(),
                    prop.getProperty("version"),
                    prop.getProperty("build")
            );
        }
        return info;
    }

    /**
     * Set a property. This takes precedence over properties found in files.
     */
    public static void setProperty(String name, String value) {
        properties.put(name, value);
    }

    /**
     * Get the random number generator.
     */
    public static Random getRandom() {
        return random;
    }

    /**
     * Set the random number generator.
     */
    public static void setRandom(Random random) {
        org.roboquant.common.Config.random = random;
    }

    /**
     * ASCII art welcome greeting including runtime info.
     */
    public static void printInfo() {
        String  msg = "No info";
        try {
            EnvInfo info = getInfo();
            msg = "             _______\n" +
                    "             | $   $ |             roboquant\n" +
                    "             |   o   |             version: " + info.getVersion() + "\n" +
                    "             |_[___]_|             build: " + info.getBuild() + "\n" +
                    "         ___ ___|_|___ ___         os: " + info.getOs() + "\n" +
                    "        ()___)       ()___)        home: " + getHome() + "\n" +
                    "       /  / |         |  \\  \\       jvm: " + info.getJvm() + "\n" +
                    "      (___) |_________| (___)      kotlin: n/a\n" +
                    "       | |   __/___\\__   | |       memory: " + info.getMemory() + "MB\n" +
                    "       /_\\  |_________|  /_\\       cpu cores: " + info.getCores() + "\n" +
                    "      // \\\\  |||   |||  // \\\\\n" +
                    "      \\\\ //  |||   |||  \\\\ //\n" +
                    "            ()__) ()__)\n" +
                    "            ///     \\\\\\\n" +
                    "         __///_     _\\\\\\__\n" +
                    "        |______|   |______|";
        } catch(ConfigurationException e) {
            logger.error("Exception during info print: ", e);
        }
        System.out.println(msg);
        System.out.flush();
    }

    /**
     * Returns the roboquant home directory, by default &lt;USER_HOME&gt;/.roboquant
     * If a system property named `roboquant.home` is set, that will be used instead.
     * If the directory does not exist yet, it will be created the first time this
     * method is called.
     */
    public static synchronized Path getHome() {
        if (home == null) {
            String roboquantHome = System.getProperty("roboquant.home");
            Path path;
            if (roboquantHome != null) {
                path = Paths.get(roboquantHome);
            } else {
                path = Paths.get(System.getProperty("user.home"), ".roboquant");
            }
            if (!Files.exists(path)) {
                try {
                    Files.createDirectory(path);
                    logger.trace("Created new home directory " + path);
                } catch (Exception e) {
                    logger.warn("Failed to create home directory: " + e.getMessage());
                }
            }
            home = path;
        }
        return home;
    }

    /**
     * Get a property value. It will try to find the property in the following order:
     * 0. Properties set using setProperty
     * 1. System properties (java -D syntax)
     * 2. Environment variables set by the OS (all capitals and underscores)
     * 3. A "dotenv" or ".env" property file in the current working directory
     * 4. The ".env" property file in roboquant home directory ($USER/.roboquant)
     *
     * If nothing is found, it will return the default value or null if not provided.
     */
    public static String getProperty(String name, String defaultValue) {
        logger.trace("finding property " + name);
        String value = getProperty(name);
        return value != null ? value : defaultValue;
    }

    /**
     * Get a property value as boolean.
     */
    public static boolean getProperty(String name, boolean defaultValue) {
        logger.trace("finding property " + name);
        String value = getProperty(name);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    /**
     * Get a property value as int.
     */
    public static int getProperty(String name, int defaultValue) {
        logger.trace("finding property " + name);
        String value = getProperty(name);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    /**
     * Return property value, or null if not found.
     */
    public static String getProperty(String name) {
        logger.trace("finding property " + name);
        
        // 0. Properties set using setProperty
        String value = properties.get(name);
        if (value != null) return value;

        // 1. System properties
        value = System.getProperty(name);
        if (value != null) return value;

        // 2. Environment variables (uppercase with underscores)
        value = System.getenv(name.replace('.', '_').toUpperCase());
        if (value != null) return value;

        // 3. Environment variables (as-is)
        value = System.getenv(name);
        if (value != null) return value;

        // 4. Environment files
        return getEnvProperty(name);
    }

    private static String getEnvProperty(String name) {
        Map<String, String> env = getEnv();
        return env.get(name);
    }

    /**
     * Load properties from an environment file. We don't use caching, so any change to the file is picked up immediately.
     */
    private static Map<String, String> getEnv() {
        Map<String, String> result = new HashMap<>();
        
        Path homePath = getHome();
        loadEnvFile(homePath.resolve(".env"), result);
        loadEnvFile(Path.of(".env"), result);
        loadEnvFile(Path.of("dotenv"), result);
        
        return result;
    }

    private static void loadEnvFile(Path path, Map<String, String> result) {
        if (Files.exists(path)) {
            Properties prop = new Properties();
            try {
                prop.load(Files.newInputStream(path));
                for (String key : prop.stringPropertyNames()) {
                    result.put(key, prop.getProperty(key));
                }
                logger.trace("Found property file at " + path);
            } catch (Exception e) {
                logger.warn("Failed to load property file: " + e.getMessage());
            }
        }
    }
}
