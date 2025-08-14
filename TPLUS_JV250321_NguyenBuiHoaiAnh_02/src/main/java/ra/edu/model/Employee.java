package ra.edu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String avatarURL;
    private boolean status;
    private LocalDateTime createdAt;
    private int departmentId;
}
