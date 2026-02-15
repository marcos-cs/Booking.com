# Booking.com Coding Challenges

Solutions to [Booking.com](http://www.booking.com/)'s coding assignment for the [Software Developer](https://workingatbooking.com/) position. Shared for educational purposes only. Use at your own risk.

## Problems

| # | Problem | Description | Solution |
|---|---------|-------------|----------|
| 1 | [Polygon Identifier](./polygon.md) | Classify quadrilaterals as square, rectangle, or other | PHP |
| 2 | [Delta Encoding](./delta-encoding.md) | Delta-encode a sequence with escape tokens | PHP |
| 3 | [Mutual Anagrams Detector](./anagram.md) | Group words that are mutual anagrams | [Java](src/main/java/com/booking/Anagram.java) |
| 4 | [Array Analyzer](./sum-array.md) | Check if any pair of elements sums to N | PHP |
| 5 | [Award Top K Hotels](./awardtophotels.md) | Rank hotels by positive/negative review keyword scores | [Java](src/main/java/com/booking/AwardTopKHotels.java) |
| 6 | [Name Chaining](./live-test.md) | Chain names where last char matches next first char | - |

### Bonus

| Problem | Description | Solution |
|---------|-------------|----------|
| Zigzag Array | Rearrange array in zigzag fashion (a < b > c < d > ...) | [Java](src/main/java/com/booking/ZigzagFlashion.java) |
| Defect Service | Filter car defects by model and year | [Java](src/main/java/com/olx/challenge/DefectService.java) |

## Tech Stack

- **Language:** Java 11
- **Build:** Maven
- **Testing:** JUnit 5 + AssertJ
- **Libraries:** Google Guava

## Getting Started

### Prerequisites

- Java 11+
- Maven 3.6+

### Build

```bash
mvn compile
```

### Run Tests

```bash
mvn test
```

### Run a Specific Solution

```bash
# Award Top K Hotels
mvn exec:java -Dexec.mainClass="com.booking.AwardTopKHotels"

# Mutual Anagrams
mvn exec:java -Dexec.mainClass="com.booking.Anagram"

# Zigzag Array
mvn exec:java -Dexec.mainClass="com.booking.ZigzagFlashion"
```

## Project Structure

```
src/
├── main/java/
│   ├── com/booking/           # Booking.com challenge solutions
│   │   ├── Anagram.java       # Mutual anagrams detector
│   │   ├── AwardTopKHotels.java  # Hotel ranking by reviews
│   │   └── ZigzagFlashion.java   # Zigzag array rearrangement
│   └── com/olx/challenge/     # OLX challenge
│       ├── Car.java
│       ├── Defect.java
│       ├── DefectApiClient.java
│       └── DefectService.java
└── test/java/
    └── com/booking/           # Unit tests
        ├── AnagramTest.java
        ├── AwardTopKHotelsTest.java
        └── ZigzagFlashionTest.java
```

## License

[The Unlicense](./LICENSE) - released into the public domain.
