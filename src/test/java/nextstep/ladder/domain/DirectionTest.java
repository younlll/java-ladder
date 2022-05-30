package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import nextstep.ladder.generator.FixedNonProductionGenerator;
import nextstep.ladder.generator.FixedProductionGenerator;
import nextstep.ladder.generator.RandomProductionGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {
    @Test
    @DisplayName("방향에 대한 객체 생성을 확인한다")
    void checkedDirectionObjectGenerate() {
        // given
        boolean left = true;

        // when
        Direction direction = Direction.first(left);

        // then
        assertAll(
                () -> assertThat(direction.isLeft()).isTrue(),
                () -> assertThat(direction.isRight()).isFalse()
        );
    }

    @Test
    @DisplayName("이전 위치에 사다리가 생성된 경우, 다음 사다리 생성을 확인한다")
    void checkedNextDirection() {
        // given
        Direction direction = Direction.first(true);

        // when
        Direction nextDirection = direction.next(new RandomProductionGenerator());

        // then
        assertAll(
                () -> assertThat(nextDirection.isLeft()).isFalse(),
                () -> assertThat(nextDirection.isRight()).isTrue()
        );
    }

    @Test
    @DisplayName("이전 위치에 사다리 생성이 되지 않은 경우, 다음 사다리 생성이 된것을 확인한다")
    void checkedNextDirectionFixedGenerate() {
        // given
        Direction direction = Direction.first(false);

        // when
        Direction nextDirection = direction.next(new FixedProductionGenerator());

        // then
        assertAll(
                () -> assertThat(nextDirection.isLeft()).isTrue(),
                () -> assertThat(nextDirection.isRight()).isFalse()
        );
    }

    @Test
    @DisplayName("이전 위치에 사다리 생성이 되지 않은 경우, 다음 사다리 생성 되지 않은 것을 확인한다")
    void checkedNextDirectionFixedNonGenerate() {
        // given
        Direction direction = Direction.first(false);

        // when
        Direction nextDirection = direction.next(new FixedNonProductionGenerator());

        // then
        assertAll(
                () -> assertThat(nextDirection.isLeft()).isFalse(),
                () -> assertThat(nextDirection.isRight()).isFalse()
        );
    }
}
