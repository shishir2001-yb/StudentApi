package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*@Repository is a Spring annotation that indicates that the decorated class is a repository.
A repository is a mechanism for encapsulating storage, retrieval, and search behavior which emulates
a collection of objects. It is a specialization of the
  @Component annotation allowing for implementation
 classes to be autodetect through classpath scanning.
 */
@Repository
public interface StudentRepository
        extends JpaRepository<Student,Long> {



    //IMPPPPPPPPPPPPPPPPPPPPPPPPPPP
    /*
        HERE Student is not the database but the Student class (Since it is Entity)
     */
    @Query("SELECT s FROM Student s WHERE s.email=?1")
    Optional<Student> findStudentByEmail(String email);

}
