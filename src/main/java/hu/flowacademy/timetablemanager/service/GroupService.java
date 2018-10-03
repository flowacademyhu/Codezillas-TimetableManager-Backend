package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.model.Group;
import hu.flowacademy.timetablemanager.repository.GroupRepository;
import hu.flowacademy.timetablemanager.service.dto.GroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public GroupDTO save(GroupDTO groupDTO) {
        Group entity = toEntity(groupDTO);
        return toDto(groupRepository.save(entity));
    }

    public List<GroupDTO> findAll() {
        return toDto(groupRepository.findAll());
    }
    // Group findOneDirect
    public GroupDTO findOne(Long id) {
        return groupRepository.findById(id)
                .map(group -> toDto(group)).orElse(null);
    }
    //
    public Group findOneDirect(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

    public List<GroupDTO> toDto(List<Group> groups) {
        return groups.stream().map(group -> {
            return toDto(group);
        })
                .collect(Collectors.toList());
    }

    private GroupDTO toDto(Group group) {
        if (group == null) {
            return null;
        }
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setName(group.getName());
        groupDTO.setLocation(group.getLocation());
        groupDTO.setUsers(group.getUsers());
        groupDTO.setClasses(group.getClasses());
        return groupDTO;
    }

    private Group toEntity(GroupDTO groupDTO) {
        if (groupDTO == null) {
            return null;
        }
        Group group = new Group();
        group.setId(groupDTO.getId());
        group.setName(groupDTO.getName());
        group.setLocation(groupDTO.getLocation());
        group.setUsers(groupDTO.getUsers());
        group.setClasses(groupDTO.getClasses());
        return group;
    }
}
