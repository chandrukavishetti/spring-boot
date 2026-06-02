# Fixes Applied

This updated project incorporates the following fixes:

1. Fixed `409 Conflict` during department creation caused by nested employee mapping through ModelMapper.
2. Updated `DepartmentServiceImpl` to manually create `Department` and `Employee` objects and attach employees only once.
3. Added `@NotNull` validation for employee salary in `EmployeeRequestDto`.
4. Added duplicate email detection inside the same request body.
5. Updated employee email repository checks to ignore case.
6. Improved update logic so employee emails belonging to the same department are not incorrectly treated as duplicates.
7. Added `IllegalArgumentException` handling in `GlobalExceptionHandler` so invalid pagination gives `400 Bad Request`, not `500 Internal Server Error`.
8. Packaged a cleaner project ZIP without `target/` and IDE metadata files.

Note: Maven is not installed in this execution environment, and the Maven wrapper could not download Maven due to external download restrictions. The project was therefore statically updated and packaged.
