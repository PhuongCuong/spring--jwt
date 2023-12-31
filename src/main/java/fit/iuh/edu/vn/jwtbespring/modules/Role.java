package fit.iuh.edu.vn.jwtbespring.modules;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String method;
    private String descriptions;

    @OneToMany(mappedBy = "role")
    @JsonBackReference
    private List<GroupRole> roles;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", descriptions='" + descriptions + '\'' +
                '}';
    }
}
