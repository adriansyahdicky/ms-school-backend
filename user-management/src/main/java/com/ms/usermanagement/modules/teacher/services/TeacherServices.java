package com.ms.usermanagement.modules.teacher.services;

import com.ms.usermanagement.base.exception.BaseException;
import com.ms.usermanagement.base.responses.PagingSortingResp;
import com.ms.usermanagement.base.responses.SingleRecordResp;
import com.ms.usermanagement.base.searchspec.GenericSpecification;
import com.ms.usermanagement.base.searchspec.SearchCriteria;
import com.ms.usermanagement.base.searchspec.SearchOperation;
import com.ms.usermanagement.config.response_messages.localization_messages.TimeEnumMessagesKey;
import com.ms.usermanagement.modules.teacher.dto.TeacherCreate;
import com.ms.usermanagement.modules.teacher.model.Teacher;
import com.ms.usermanagement.modules.teacher.repository.TeacherRepository;
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
import static com.ms.usermanagement.modules.student.constant.StudentFilterConstant.ADDRESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherServices {
    private final TeacherRepository teacherRepository;

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

        GenericSpecification<Teacher> specification =
                buildSpecification(search);

        Page<Teacher> pageResult=teacherRepository.findAll(specification,
                pageable);

        return PagingSortingResp.responseBuilder()
                .records(pageResult.getContent())
                .currentPage(pageResult.getNumber())
                .currentSize(pageResult.getSize())
                .totalPage(pageResult.getTotalPages())
                .totalSize(pageResult.getTotalElements())
                .build();
    }

    private GenericSpecification<Teacher> buildSpecification(Map<String, Object> search) {
        GenericSpecification<Teacher> genericSpecification = new GenericSpecification<>();
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

    public SingleRecordResp save(TeacherCreate dto) {
        if(ObjectUtils.isNotEmpty(teacherRepository.findByNameIgnoreCase(dto.getName()))){
            throw new BaseException(TimeEnumMessagesKey.ERROR_ALREADY_EXISTS,
                    "Teacher");
        }
        return SingleRecordResp.
                responseBuilder()
                .result(teacherRepository.save(Teacher.builder()
                        .name(dto.getName())
                        .address(dto.getAddress())
                        .phoneNumber(dto.getPhoneNumber())
                        .birthDate(dto.getBirthDate())
                        .placeBirth(dto.getPlaceBirth())
                        .build()))
                .build();
    }

    public SingleRecordResp findById(String id){
        return teacherRepository.findById(id)
                .map(teacher -> SingleRecordResp.
                        responseBuilder()
                        .result(teacher)).orElseThrow(() ->
                        new BaseException(TimeEnumMessagesKey.ERROR_NOT_FOUND,
                                "Teacher")).build();
    }
}
