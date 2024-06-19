package hackeru.fridarik.snipsnapp.service;

import hackeru.fridarik.snipsnapp.dto.*;
import hackeru.fridarik.snipsnapp.entity.Barber;
import hackeru.fridarik.snipsnapp.error.PaginationException;
import hackeru.fridarik.snipsnapp.error.ResourceNotFoundException;
import hackeru.fridarik.snipsnapp.repository.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BarberServiceImpl implements BarberService {

    //props:
    private final BarberRepository barberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public BarberResponseDTO createBarber(BarberCreateDTO dto) {
        if(barberRepository.existsBarberByEmail(dto.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return modelMapper.map(barberRepository.save(modelMapper.map(dto, Barber.class)), BarberResponseDTO.class);
    }


    @Override
    public EntityListDTO<BarberResponseDTO> getAllBarbers(int pageNo, int pageSize, String sortDir, String... sortBy) {


        try {
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.fromString(sortDir), sortBy);
            Page<Barber> bp = barberRepository.findAll(pageable);

            if (pageNo > bp.getTotalPages()) {
                throw new PaginationException("Page Number " + pageNo + " Exceeds totalPages " + bp.getTotalPages());
            }

            return new EntityListDTO<BarberResponseDTO> (
                    bp.getTotalElements(),
                    bp.getNumber(),
                    bp.getSize(),
                    bp.getTotalPages(),
                    bp.isFirst(),
                    bp.isLast(),
                    bp.getContent().stream().map(b -> modelMapper.map(b, BarberResponseDTO.class)).toList()
            );
        } catch (IllegalArgumentException e){
            throw new PaginationException(e.getMessage());
        }
    }


    @Override
    public BarberResponseDTO getBarberById(long id) {
        return modelMapper.map(getBarberEntityOrThrow(id), BarberResponseDTO.class);
    }

    @Override
    public BarberResponseDTO updateBarberInfo(long id, BarberCreateDTO dto) {

        //check that the entity exists:
        Barber barber = getBarberEntityOrThrow(id);

        //update all the entity props:
        barber.setName(dto.getName());
        barber.setSkills(dto.getSkills());
        barber.setExperience(dto.getExperience());
        barber.setWorkingHours(dto.getWorkingHours());
        barber.setAddress(dto.getAddress());
        barber.setPhone(dto.getPhone());

        //save to update and return the response.
        return modelMapper.map(barberRepository.save(barber), BarberResponseDTO.class);
    }


    @Override
    public BarberResponseDTO deleteBarber(long id) {
        BarberResponseDTO barberDto = modelMapper.map(getBarberEntityOrThrow(id), BarberResponseDTO.class);;
        barberRepository.deleteById(id);
        return barberDto;
    }


    @Override
    public Barber getBarberEntityOrThrow(long id) {
        return barberRepository.findById(id).orElseThrow(ResourceNotFoundException.newInstance("Barber", "id", id)
        );
    }

    @Override
    public List<BarberResponseDTO> searchByName(String name){
        String lowerCaseName = name.toLowerCase();
        List<Barber> barberLst = barberRepository.findAll();
        return barberLst.stream()
                .filter( b -> b.getName().toLowerCase().contains(lowerCaseName))
                .map(b -> modelMapper.map(b, BarberResponseDTO.class))
                .collect(Collectors.toList());
    }
}
