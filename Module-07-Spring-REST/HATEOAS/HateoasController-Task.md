# Task Description

Create a HATEOAS-enabled REST controller. Use Spring HATEOAS with EntityModel and CollectionModel. For GET /api/employees/{id}, return EntityModel&lt;Employee&gt; with self-link (linkTo(methodOn(EmployeeController.class).getEmployee(id)).withSelfRel()), link to all employees (withRel(""employees"")), and link to update (withRel(""update"")). For GET /api/employees, return CollectionModel with self-link and individual item links. Explain how HATEOAS makes APIs self-discoverable and reduces client-server coupling.
