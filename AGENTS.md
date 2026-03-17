# AGENTS.md - Guidelines for Agentic Coding in roboquant

This file provides guidelines for agentic coding agents operating in the roboquant repository.

## Project Overview

Roboquant is an algorithmic trading platform written in Kotlin. It's a Maven-based multi-module project with the core module `roboquant` and several extension modules (alpaca, ibkr, charts, jupyter, questdb, avro, ssr).

## Build Commands

### Basic Commands
```bash
# Build entire project (skips IBKR module which requires proprietary dependencies)
./mvnw clean install -pl '!roboquant-ibkr'

# Run all unit tests
./mvnw test

# Run all tests including integration tests
./mvnw clean verify

# Run tests for a specific module
./mvnw test -pl roboquant

# Run a single test class
./mvnw test -Dtest=EMACrossoverTest -pl roboquant

# Run a single test method
./mvnw test -Dtest=EMACrossoverTest#simpleTest -pl roboquant
```

### Code Quality
```bash
# Run detekt linter (configured in docs/run/detekt.yml)
./mvnw detekt:check

# Skip tests during build
./mvnw clean install -DskipTests
```

### Module Structure
- `roboquant` - Core library
- `roboquant-alpaca` - Alpaca broker integration
- `roboquant-ibkr` - Interactive Brokers integration (requires proprietary TWS)
- `roboquant-charts` - Charting functionality
- `roboquant-jupyter` - Jupyter notebook support
- `roboquant-questdb` - QuestDB time-series database
- `roboquant-avro` - Avro serialization
- `roboquant-ssr` - Server-side rendering for charts

## Code Style Guidelines

### License Header
All source files must include the Apache 2.0 license header:
```kotlin
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
```

### Naming Conventions
- Classes: PascalCase (e.g., `EMACrossover`, `SimBroker`)
- Functions: camelCase (e.g., `createSignals`, `playBackground`)
- Constants: UPPER_SNAKE_CASE (e.g., `TOTAL_BAR_LENGTH`)
- Package: lowercase (e.g., `org.roboquant.common`)
- Test classes: `<ClassName>Test` suffix (e.g., `EMACrossoverTest`)
- Test methods: camelCase, no underscores (e.g., `simpleTest`, not `simple_test`)

### Import Organization
Organize imports in the following order with a blank line between groups:
1. Kotlin internal imports
2. Java/JVM imports
3. Third-party library imports
4. Roboquant internal imports

```kotlin
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.runBlocking
import java.time.Instant
import java.util.*
import kotlin.math.min
import kotlin.math.roundToInt
import org.roboquant.common.Account
import org.roboquant.brokers.Broker
```

### Documentation
- Public APIs require KDoc documentation
- Use `@constructor` tag for constructor descriptions
- Document parameters with `@param`
- Document return values with `@return` or `@property`
- Keep documentation concise but complete

### Error Handling
- Use custom exception classes from `org.roboquant.common`:
  - `RoboquantException` - base exception
  - `UnsupportedException` - for unsupported operations
  - `ConfigurationException` - for configuration errors
  - `ValidationException` - for validation errors
  - `DoesNotComputeException` - for computation failures
  - `NoTradingException` - for non-trading days

- Avoid generic `Exception` or `Throwable` in catch blocks
- Use lazy logging with lambda producers:
```kotlin
logger.trace { "finding property $name" }
```

### Types and Null Safety
- Prefer immutable data structures where possible
- Use nullable types (`?`) only when necessary
- Prefer explicit type declarations for public APIs
- Use `val` by default, `var` only when mutation is required

### Logging
Use the `Logging` utility from `org.roboquant.common`:
```kotlin
private val logger = Logging.getLogger(Config::class.java)
logger.trace { "message" }
logger.debug { "message" }
logger.info { "message" }
logger.warn { "message" }
logger.error { "message" }
```

### Detekt Rules (from docs/run/detekt.yml)
Key rules enforced:
- Max method length: 60 lines
- Max cyclomatic complexity: 15
- Max nested block depth: 6
- Max parameters: 8 for functions, 10 for constructors
- No empty catch blocks (use `catch (_: ClosedReceiveChannelException)` for ignored exceptions)
- No throwing generic exceptions

### Testing Conventions
- Test classes go in `src/test/kotlin` mirroring the main source structure
- Use `kotlin.test` framework (`@Test` annotation)
- Use `TestData` object from `org.roboquant` for common test fixtures
- Test naming: `<MethodName>Test` for class, `<testName>` for methods
- Integration tests are marked with `IT` suffix (e.g., `IBKRBrokerTestIT`)
- Integration tests are skipped by default (`-DskipIntegrationTests=false` to run)

### Coroutines
- Use `runBlocking` sparingly, prefer suspend functions
- Dispatchers: use `Dispatchers.Default` for CPU-bound work
- Avoid blocking operations in coroutines

## Key File Locations

- Configuration: `pom.xml` (parent and module-specific)
- Linting rules: `docs/run/detekt.yml`
- Documentation: `docs/`
- Test resources: `src/test/resources/data/`
