package api_filmes.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import api_filmes.domain.dto.user.UserRequestDTO;
import api_filmes.domain.dto.user.UserResponseDTO;
import api_filmes.domain.entities.User;
import api_filmes.domain.exception.BadRequestException;
import api_filmes.domain.exception.ResourceNotFoundException;
import api_filmes.domain.repository.UserRepository;

@Service
public class UserService implements ICRUDService<UserRequestDTO, UserResponseDTO> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapper.map(user, UserResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getById(Long id) {
        // @Daniel oque vc acha do encontrar por email?
        // Optional optionalUser = userRepository.findByEmail()
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("Usuário não encontrado!");
        }
        return mapper.map(optionalUser.get(), UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO create(UserRequestDTO dto) {
        if (dto.getEmail() == null || dto.getPassword() == null || dto.getName() == null) {
            throw new BadRequestException("Preencha todos os campos!");
        }
        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());
        if (optionalUser.isPresent()) {
            throw new BadRequestException("Esse usuário já está cadastrado!");
        }
        User user = mapper.map(dto, User.class);
        String password = passwordEncoder.encode(user.getPassword());
        user.setEmail(dto.getEmail());
        user.setPassword(password);
        user.setId(null);
        user.setActivationDate(new Date());
        user = userRepository.save(user);
        return mapper.map(user, UserResponseDTO.class);
    }

    // TODO: Criptografar a senha ao fazer a atualização dos dados do usuário 
    @Override
    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        UserResponseDTO userDb = (UserResponseDTO) getById(id); 
        /*
        * Se quiser implementar verificação certa de email, pode se busca o email, que já vai estar cadastrado. Aì o certo é verificar se o email está em outro id, ou seja, é de outro usuário, se os ids forem diferentes não permitir atualização, se for igual permitir.
        */
        if (dto.getEmail() == null || dto.getPassword() == null || dto.getName() == null) {
            throw new BadRequestException("Preencha todos os campos!");
        }
        User user = mapper.map(dto, User.class);
        user.setActivationDate(userDb.getActivationDate());
        String password = passwordEncoder.encode(dto.getPassword());
        user.setPassword(password);
        user.setId(id);
        user.setActivationDate(userDb.getActivationDate());
        user.setInactivationDate(userDb.getInactivationDate());
        user = userRepository.save(user);
        return mapper.map(user, UserResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        // Fazendo deleção e não inativação, se não usar inativação, pode apagar esse método
        getById(id);
        userRepository.deleteById(id);
    }
    
}
