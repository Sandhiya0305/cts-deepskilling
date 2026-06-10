# Task Description

Write Mockito-based unit tests for a UserService that depends on UserRepository. Use @ExtendWith(MockitoExtension.class), @Mock for UserRepository, @InjectMocks for UserService. Test scenarios: findUserById returns user when found, findUserById returns null or throws exception when not found, saveUser calls repository.save() exactly once, deleteUser calls repository.deleteById(). Use verify() to confirm interactions, when().thenReturn() to stub methods, and ArgumentMatchers.any() for flexible matching. Show how mocking isolates the service layer from database dependencies.
