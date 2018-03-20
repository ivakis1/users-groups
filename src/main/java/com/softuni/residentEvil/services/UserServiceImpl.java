package com.softuni.residentEvil.services;

import com.softuni.residentEvil.entities.User;
import com.softuni.residentEvil.models.binding.EditUserModel;
import com.softuni.residentEvil.models.binding.RegisterUser;
import com.softuni.residentEvil.models.view.ViewUser;
import com.softuni.residentEvil.repositories.UserRepository;
import com.softuni.residentEvil.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired	
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

	@Override
	public User findByUsername(final String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(final RegisterUser userDTO) {
		User user = ModelParser.getInstance().map(userDTO, User.class);
		user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		//user.setAuthorities(Set.of(roleRepository.findByRole("ROLE_USER")));
		this.userRepository.save(user);
	}

	@Override
	public List<ViewUser> findAllUsers(final Principal principal) {
		List<User> allUsers = this.userRepository.findAll();
		List<ViewUser> userDTOs = new LinkedList<>();
		for (User user : allUsers) {
			if (user.getUsername().equals(principal.getName())) continue;

			ViewUser viewUser = new ViewUser();
			viewUser.setUsername(user.getUsername());
			viewUser.setId(user.getId());
			userDTOs.add(viewUser);
		}
		return userDTOs;
	}

    @Override
    public ViewUser findUserById(final Long id) {
        User user = this.userRepository.getOne(id);
        ViewUser viewUser = new ViewUser();
        viewUser.setUsername(user.getUsername());
        viewUser.setId(id);
        return viewUser;
	}

    @Override
    public void editUser(@Valid EditUserModel editUserModel) {
        User user = this.userRepository.getOne(editUserModel.getId());
        user.setUsername(editUserModel.getUsername());

        this.userRepository.saveAndFlush(user);
	}

    public User loadUserByUsername(final String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }
}
