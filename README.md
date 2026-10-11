# Snake Game — Software Testing Final Project

A Java Swing Snake game used to demonstrate JUnit 5 testing, control-flow paths, data-flow (DU) analysis, boundary-value testing, equivalence partitioning, JaCoCo coverage, and GitHub Actions CI.

## Requirements

Java 17 and Maven 3.x. The game is graphical; automated tests run in headless mode.

## Build and test

```bash
mvn clean verify
```

Maven compiles production code from `src/main/java` and discovers JUnit tests in `src/test/java`. Surefire XML results appear in `target/surefire-reports/`. JaCoCo HTML coverage appears at `target/site/jacoco/index.html`; open it in a browser and click `com.zetcode` then `Board` for highlighted line/branch details. `mvn clean verify` is important because the JaCoCo HTML report runs in the `verify` phase.

To run the game, compile with `mvn package`, then launch `com.zetcode.Snake` from an IDE. The image paths in the original game remain `src/resources/*.png`, so run from the project root.

## Test classes and techniques

| File | Techniques | Example |
| --- | --- | --- |
| `BoardCollisionTest` | black-box partitions, boundary values, white-box paths | x = -1, 0, 299, 300; self-collision at segment 5 |
| `BoardMovementTest` | DU chains, path-based | old head x/y used for body; each direction writes head x/y |
| `BoardAppleAndPathTest` | CFG paths, equivalence partitions | both apple coordinates match vs only one matches; tick active/inactive |
| `BoardKeyboardTest` | decision/path tests | disallow direct reversal, allow perpendicular turn |
| `BoardTestSupport` | maintainability | common reflection helpers, setup, timer cleanup |

The tests use reflection because the original game's behavior is in private methods. This avoids changing gameplay, but a later refactor should separate game logic from Swing UI for cleaner public APIs.

## Continuous integration

The workflow `.github/workflows/maven.yml` runs on pushes, pull requests, and manual dispatch. It installs JDK 17, runs `mvn -B clean verify`, and uploads the Surefire and JaCoCo reports as a downloadable Actions artifact, even when a test fails. The build should be considered successful only after GitHub displays a green check and the Surefire results show tests executed.

To capture the required screenshots: (1) open the Actions tab and screenshot a **successful** `Java CI with Maven` run; (2) download its `test-and-coverage-reports` artifact; (3) open `target/site/jacoco/index.html` and screenshot the overall summary; (4) click `com.zetcode` then `Board` and screenshot the class/line detail. Record the actual percentages from the generated report, not estimates.

## Known risks and next steps

- The original `move()` writes `x[dots]` and `y[dots]`; at maximum length this may exceed the array size.
- The apple can spawn on an occupied snake cell.
- Collision checks happen before movement during each timer tick; a boundary crossing may be detected on the next tick.
- The UI, random apple generation, and game logic are tightly coupled; these make deterministic testing harder.
- A future refactor should extract a game-state class, inject a predictable random-number generator, and add explicit tests for full-length snake and rendering behavior.

See `TESTING_NOTES.md` for the CFG/DU map and coverage interpretation worksheet.

## Downloading the JaCoCo coverage report from GitHub Actions

The CI workflow runs the JUnit suite and explicitly generates the JaCoCo HTML report. After a successful Actions run, open the run and download the `jacoco-html-report` artifact. Unzip it and open `index.html` in a web browser. The summary page contains the coverage table and coverage bars. Click `com.zetcode` and then `Board` for the detailed source coverage view.
