package com.ms.usermanagement.modules.student.services;

import com.ms.usermanagement.base.exception.BaseException;
import com.ms.usermanagement.base.responses.PagingSortingResp;
import com.ms.usermanagement.base.responses.SingleRecordResp;
import com.ms.usermanagement.base.searchspec.GenericSpecification;
import com.ms.usermanagement.base.searchspec.SearchCriteria;
import com.ms.usermanagement.base.searchspec.SearchOperation;
import com.ms.usermanagement.config.response_messages.localization_messages.TimeEnumMessagesKey;
import com.ms.usermanagement.modules.student.dto.StudentCreate;
import com.ms.usermanagement.modules.student.model.Student;
import com.ms.usermanagement.modules.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.ms.usermanagement.modules.student.constant.StudentFilterConstant.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServices {

    private final StudentRepository studentRepository;

    public PagingSortingResp view(int page,
                                  int size,
                                  Map<String, Object> search,
                                  String sort,
                                  String sortDescription){
        Sort.Direction direction = sortDescription.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        if (sort == null)
            sort = CREATED_AT;
        String field = CREATED_AT;

        if (sort.equals(NAME))
            field = NAME;
        else if (sort.equals(ADDRESS))
            field = ADDRESS;

        PageRequest pageable = PageRequest.of(page, size, Sort.by(new Sort.Order(direction, field).ignoreCase()));

        GenericSpecification<Student> specification =
                buildSpecification(search);

        Page<Student> pageResult=studentRepository.findAll(specification,
                pageable);

        return PagingSortingResp.responseBuilder()
                .records(pageResult.getContent())
                .currentPage(pageResult.getNumber())
                .currentSize(pageResult.getSize())
                .totalPage(pageResult.getTotalPages())
                .totalSize(pageResult.getTotalElements())
                .build();
    }

    private GenericSpecification<Student> buildSpecification(Map<String, Object> search) {
        GenericSpecification<Student> genericSpecification = new GenericSpecification<>();
        search.forEach((k, v) -> {

            Optional.of(k).filter(s -> s.equalsIgnoreCase(NAME)
                    && ObjectUtils.isNotEmpty(v)).ifPresent(s ->
                    genericSpecification.add(new SearchCriteria(NAME, v, SearchOperation.MATCH)));

            Optional.of(k).filter(s -> s.equalsIgnoreCase(ADDRESS)
                    && ObjectUtils.isNotEmpty(v)).ifPresent(s ->
                    genericSpecification.add(new SearchCriteria(ADDRESS, v, SearchOperation.MATCH)));
        });
        return genericSpecification;
    }

    public SingleRecordResp save(StudentCreate dto) {
        if(ObjectUtils.isNotEmpty(studentRepository.findByNameIgnoreCase(dto.getName()))){
            throw new BaseException(TimeEnumMessagesKey.ERROR_ALREADY_EXISTS,
                    "Student");
        }
        return SingleRecordResp.
                responseBuilder()
                .result(studentRepository.save(Student.builder()
                                .name(dto.getName())
                                .address(dto.getAddress())
                                .motherName(dto.getMotherName())
                                .fatherName(dto.getFatherName())
                                .phoneNumber(dto.getPhoneNumber())
                                .birthDate(dto.getBirthDate())
                                .placeBirth(dto.getPlaceBirth())
                        .build()))
                .build();
    }

    public SingleRecordResp findById(String id){
        return studentRepository.findById(id)
                .map(student -> SingleRecordResp.
                         responseBuilder()
                         .result(student)).orElseThrow(() ->
                        new BaseException(TimeEnumMessagesKey.ERROR_NOT_FOUND,
                "Student")).build();
    }

}
