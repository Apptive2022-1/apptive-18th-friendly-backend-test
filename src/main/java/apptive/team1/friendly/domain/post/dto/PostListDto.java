package apptive.team1.friendly.domain.post.dto;

import apptive.team1.friendly.domain.user.data.dto.profile.ProfileImgDto;
import apptive.team1.friendly.global.common.s3.FileInfo;
import apptive.team1.friendly.domain.post.entity.HashTag;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
public class PostListDto {
    // 게시판 리스트에 보이는 필드
    private Long postId;

    private String title;

    private int maxPeople;

    private LocalDateTime promiseTime;

    private String location;

    private Set<HashTag> hashTag = new HashSet<>();

    // 이미지 필드
    private ProfileImgDto profileImgDto;

}
