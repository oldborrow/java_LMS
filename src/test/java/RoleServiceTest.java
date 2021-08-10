import com.example.demo.controller.NotFoundException;
import com.example.demo.dao.RoleRepository;
import com.example.demo.domain.Role;
import com.example.demo.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoleServiceTest {
    @Test
    void getDefaultRole() {
        RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
        RoleService roleService = new RoleService(roleRepository);
        Mockito.when(roleRepository.findByName("ROLE_STUDENT"))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, roleService::getDefaultRole);
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_STUDENT");
        Mockito.when(roleRepository.findByName("ROLE_STUDENT")).thenReturn(Optional.of(role));
        Assertions.assertDoesNotThrow(roleService::getDefaultRole);
        Role defaultRole = roleService.getDefaultRole();
        Assertions.assertEquals("ROLE_STUDENT", defaultRole.getName());
    }
}
