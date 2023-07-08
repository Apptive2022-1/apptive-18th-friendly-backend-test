package apptive.team1.friendly.domain.post.dto;

import apptive.team1.friendly.domain.user.data.dto.profile.ProfileImgDto;
import apptive.team1.friendly.domain.post.entity.HashTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
public class PostListDto {

    private Long postId;

    private PostImageDto postImageDto;

    private String title;

    private int maxPeople;

    private LocalDateTime promiseTime;

    private String location;

    private String description;

    private Set<HashTag> hashTags = new HashSet<>();

    @Builder
    public PostListDto(Long postId, PostImageDto postImageDto, String title, int maxPeople, LocalDateTime promiseTime, String location, String description, Set<HashTag> hashTags) {
        this.postId = postId;
        this.postImageDto = postImageDto;
        this.title = title;
        this.maxPeople = maxPeople;
        this.promiseTime = promiseTime;
        this.location = location;
        this.description = description;
        this.hashTags = hashTags;
    }

}
