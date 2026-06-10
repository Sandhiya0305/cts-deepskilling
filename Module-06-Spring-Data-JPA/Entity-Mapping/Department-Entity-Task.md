# Task Description

Create JPA entities Department and Employee demonstrating bidirectional relationships. Department has @OneToMany(mappedBy=""department"", cascade=CascadeType.ALL, fetch=FetchType.LAZY) List&lt;Employee&gt;. Employee has @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name=""dept_id"") Department. Show how to avoid infinite recursion in toString() using @JsonIgnore or @ToString.Exclude. Demonstrate cascade operations: saving a department saves its employees, deleting a department deletes employees. Show orphanRemoval=true for automatic cleanup of removed employees.
