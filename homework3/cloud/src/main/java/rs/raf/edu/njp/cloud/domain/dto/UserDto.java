package rs.raf.edu.njp.cloud.domain.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;

    public UserDto() {
    }

    public UserDto(Long id, String username, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
