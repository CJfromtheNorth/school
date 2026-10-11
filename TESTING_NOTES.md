# Test design, CFG paths, and evidence checklist

## Simplified CFG for `checkApple()`

```
Start -> x[0] == apple_x?
         | no -> End
         | yes -> y[0] == apple_y?
                  | no -> End
                  | yes -> dots++ -> locateApple() -> End
```

Independent feasible paths: X mismatch; X match/Y mismatch; both match. `BoardAppleAndPathTest` exercises all three.

## Simplified CFG for `actionPerformed()`

```
Start -> inGame?
         | false -> repaint -> End
         | true  -> checkApple -> checkCollision -> move -> repaint -> End
```

`tickMovesSnakeWhenGameIsActive` and `tickDoesNotMoveSnakeWhenGameIsOver` cover both branches.

## Data-flow definitions and uses

| Variable | Definition | Use | Test |
| --- | --- | --- | --- |
| `x[0]`, `y[0]` | initial values / head placement | movement and collision checks | `BoardMovementTest`, `BoardCollisionTest` |
| `x[z]`, `y[z]` | copied from preceding segment in `move()` | next move / self-collision | `oldBodyCoordinatesPropagateToNextSegment` |
| `dots` | `initGame()` assigns 3; `checkApple()` increments | `move()` loop and drawing loop | `initialGameStateIsThreeSegmentsAtExpectedCoordinates`, `eatingAppleIncreasesLengthAndRelocatesApple` |
| `inGame` | starts true; collision sets false | `actionPerformed()` branch | `tickDoesNotMoveSnakeWhenGameIsOver` |
| direction flags | keyboard listener updates | conditional updates in `move()` | `BoardKeyboardTest`, `movementPaths` |

These are selected DU chains, not a claim of exhaustive all-DU-path coverage.

## Coverage review worksheet (fill in from JaCoCo)

- Instructions covered: ___%
- Branches covered: ___%
- Lines covered: ___%
- Methods covered: ___%
- Tests run / failures / errors: ___ / ___ / ___
- Uncovered method/line: ___
- Reason it was missed: ___
- Should it be tested? Why? ___

Potential uncovered areas include `Snake`'s graphical entry point and `Board` rendering (`paintComponent`, `doDrawing`, `gameOver`). GUI rendering needs additional setup or integration tests; omitting it from unit tests should be explicitly justified. Check the actual JaCoCo report before describing any particular line as uncovered.

## Submission checklist

- [ ] Push updated source/tests/workflow to GitHub.
- [ ] Confirm green GitHub Actions run with tests > 0.
- [ ] Download JaCoCo HTML artifact.
- [ ] Screenshot overall JaCoCo summary.
- [ ] Screenshot class/line-level JaCoCo view.
- [ ] Screenshot green CI run.
- [ ] Insert real coverage percentages and screenshots into final 3–4 page report.
