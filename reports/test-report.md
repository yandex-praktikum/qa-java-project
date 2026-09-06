# Test Report

Date: 2026-09-06 21:56 MSK

Command:

```shell
mvn clean verify
```

Result: BUILD SUCCESS

## Unit Tests

| Test class | Tests | Failures | Errors | Skipped | Time |
| --- | ---: | ---: | ---: | ---: | ---: |
| `praktikum.BurgerReceiptParameterizedTest` | 2 | 0 | 0 | 0 | 1.295 s |
| `praktikum.BurgerTest` | 10 | 0 | 0 | 0 | 0.081 s |
| **Total** | **12** | **0** | **0** | **0** | **1.376 s** |

## Coverage

JaCoCo analyzed `praktikum.Burger`.

| Counter | Missed | Covered | Coverage |
| --- | ---: | ---: | ---: |
| Instructions | 0 | 133 | 100% |
| Branches | 0 | 4 | 100% |
| Lines | 0 | 21 | 100% |
| Complexity | 0 | 9 | 100% |
| Methods | 0 | 7 | 100% |

Generated reports after running the command:

- Surefire: `target/surefire-reports`
- JaCoCo HTML: `target/site/jacoco/index.html`
- JaCoCo XML/CSV: `target/site/jacoco/jacoco.xml`, `target/site/jacoco/jacoco.csv`
