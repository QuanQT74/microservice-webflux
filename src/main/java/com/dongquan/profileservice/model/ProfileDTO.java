package com.dongquan.profileservice.model;

import com.dongquan.profileservice.data.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDTO {

    private Long id;
    private String email;
    private String status;
    private String name;
    private Double initialBalance;
    private String role;

    public static Profile dtoToEntity(ProfileDTO dto) {
        Profile profile = new Profile();
        profile.setId(dto.getId());
        profile.setEmail(dto.getEmail());
        profile.setName(dto.getName());
        profile.setStatus(dto.getStatus());
        profile.setRole(dto.getRole());
        return profile;
    }

    public static ProfileDTO entityToDto(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setEmail(profile.getEmail());
        profileDTO.setName(profile.getName());
        profileDTO.setRole(profile.getRole());
        profileDTO.setStatus(profile.getStatus());
        return profileDTO;
    }
}