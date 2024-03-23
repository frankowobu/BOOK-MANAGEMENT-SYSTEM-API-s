package com.example.libraryApplication.service.librarian;

import com.example.libraryApplication.dto.usersdto.LibrarianDto;
import com.example.libraryApplication.entity.Librarian;
import com.example.libraryApplication.entity.Role;
import com.example.libraryApplication.entity.Users;
import com.example.libraryApplication.exception.LibrarianFoundException;
import com.example.libraryApplication.repository.LibrarianRepository;
import com.example.libraryApplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {
    LibrarianRepository librarianRepository;
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createLibrarian(LibrarianDto librarianDto) {
        Optional<Librarian> optionalLibrarian = librarianRepository.findByEmail(librarianDto.getEmail());
        if (!optionalLibrarian.isPresent()){
            Librarian createLibrarian = new Librarian();
            createLibrarian.setFirstName(librarianDto.getFirstName());
            createLibrarian.setLastName(librarianDto.getLastName());
            createLibrarian.setEmail(librarianDto.getEmail());
            createLibrarian.setPassword(passwordEncoder.encode(librarianDto.getPassword()));
            createLibrarian.setRole(Role.LIBRARIAN);

            //create User account for the librarian
            Users user = new Users();
            user.setEmail(librarianDto.getEmail());
            user.setPassword(passwordEncoder.encode(librarianDto.getPassword()));
            user.setRole(Role.LIBRARIAN);
            user.setLibrarian(createLibrarian);
            userRepository.save(user);

            //save to librarian db
            createLibrarian.setUser(user);
            librarianRepository.save(createLibrarian);
        }
        else throw new LibrarianFoundException(librarianDto.getEmail());
    }

    @Override
    public Librarian getLibrarian(Long librarianId) {
        Optional<Librarian> optionalLibrarian = librarianRepository.findById(librarianId);
        if (optionalLibrarian.isPresent()){
            return optionalLibrarian.get();
        }
        else throw new IllegalArgumentException("not found in the database");
    }

    @Override
    public String updateLibrarian(LibrarianDto librarianDto) {
        Librarian updateLibrarian = getLibrarian(librarianDto.getId());
        updateLibrarian.setFirstName(librarianDto.getFirstName());
        updateLibrarian.setLastName(librarianDto.getLastName());
        updateLibrarian.setEmail(librarianDto.getEmail());

        Users updateUser = updateLibrarian.getUser();
        updateUser.setEmail(librarianDto.getEmail());

        librarianRepository.save(updateLibrarian);
        return "Librarian updated successfully.";
    }

    @Override
    public LibrarianDto convertLibrarianToLibrarianDto(Librarian librarian) {
        LibrarianDto librarianDto = new LibrarianDto();
        librarianDto.setId(librarian.getId());
        librarianDto.setFirstName(librarian.getFirstName());
        librarianDto.setLastName(librarian.getLastName());
        librarianDto.setEmail(librarian.getEmail());
        return librarianDto;
    }

    @Override
    public String deleteLibrarian(Long librarianId) {
        Librarian deleteLib = getLibrarian(librarianId);
        librarianRepository.deleteById(deleteLib.getId());
        return deleteLib.getFirstName() + " has been deleted";
    }

    @Override
    public List<LibrarianDto> getAllLibrarian() {
        List<Librarian> librarianList = (List<Librarian>) librarianRepository.findAll();
        List<LibrarianDto> librarianDtoList = new ArrayList<>();
        for (Librarian item : librarianList){
            librarianDtoList.add(convertLibrarianToLibrarianDto(item));
        }
        return librarianDtoList;
    }
}