# Task Description

Create paginated and sorted query methods. In repository, extend PagingAndSortingRepository or use Pageable parameter. In service, create method findEmployees(int page, int size, String sortBy, String direction). Use PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy)). Return Page&lt;EmployeeDTO&gt; with metadata: totalElements, totalPages, currentPage, hasNext, hasPrevious. Support sorting by name, salary, joinDate. Handle invalid sort fields with fallback to default. Demonstrate in controller with query parameters.
