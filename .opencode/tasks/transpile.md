
## Goal

The high level goal is to transpile Kotlin files from directory `roboquant/src/main/kotlin/org/robok/` to Java files 
in directory `roboquant/src/main/java/org/roboquant/`. The key constraints were:
1. Use manual transpilation (not automated tools)
2. Transpile only the `common`, `feeds`, and `journals` packages - start with 'common' package
3. Follow best practices for translating Kotlin concepts to Java
4. Use standard thread-based async (not coroutines)
5. Use Java equivalents (not Kotlin stdlib)
6. Maintain the same directory structure

## Instructions

- Use the `roboquant/src/main/kotlin/org/robok/` directory as the Kotlin source directory
- Use the `roboquant/src/main/java/org/roboquant/` directory as the Java target directory
- Ignore the `roboquant/src/test/` directory
- Only transpile Kotlin files where no hand-written Java exists (Java files without `@Metadata` annotation should be preserved)
- Only write,replace or update Java files that have an `@Metadata` annotation (auto-transpiled from Kotlin) - ignore Java files that contain **no** `@Metadata` annotation
- Use `BlockingQueue` for coroutine `Channel` replacements
- Start with the `common` package before moving to other packages
- Create clean Java code without Kotlin dependencies

## Discoveries and How-To

- The repository already had many Java files auto-transpiled from Kotlin (marked with `@Metadata` annotation)
- A lot of Java files were hand-written without a `@Metadata` annotation - these files MUST be preserved
- Kotlin `object` (singleton) pattern translates well to Java with static `INSTANCE` field
- Kotlin coroutines (`CoroutineScope`, `launch`) translate to Java `ExecutorService` + `Future`
- Kotlin extension functions become static methods in `*Kt.java` files
- Kotlin `data class` becomes Java class with explicit `equals()`, `hashCode()`, `toString()`
- Kotlin `fun interface` becomes Java `@FunctionalInterface`
- The codebase uses SLF4J for logging, which the Java code continues to use
- `Config.java` uses lazy initialization pattern for `info` and `home` fields

## Accomplished

- Package src/main/kotlin/org/robok/feed/ is already transpiled

---

