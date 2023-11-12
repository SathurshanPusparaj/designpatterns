package org.projectx.designpatterns;

import java.time.LocalDateTime;
import java.util.Optional;

public class ResultPattern {

    public static void main(String[] args) {
        //This pattern help to document the error and used in DDD and EDD
    }

    class FollowerService {

        public Optional<Result> startFollowing(String userId, String followedId, LocalDateTime dateTime) {

            if (userId.equals(followedId)) {
                //Optional.of("Can't follow yourself");
                return Optional.of(Result.failure(FollowerErrors.SameUser));
            }

            if (dateTime.isBefore(LocalDateTime.now())) {
                return Optional.of(Result.failure(FollowerErrors.InvalidDate));
            }

            // perform some actions

            return Optional.of(Result.success());
        }

    }

    public record Error (String Code, String description) {
        public static Error None = new Error("", "");
    }

    public class FollowerErrors {
        public static Error SameUser = new Error("Followers.SameUser", "Can't follow yourself");
        public static Error InvalidDate = new Error("Followers.InvalidTime", "The time passed is not a valid Time");
    }

    public static class Result {

        private final boolean isSuccess;

        private final Error error;

        private Result() {
            this.isSuccess = true;
            this.error = Error.None;
        }

        private Result(boolean isSuccess, Error error) {
            if (isSuccess && error != Error.None ||
            !isSuccess && error == Error.None) {
                throw new IllegalArgumentException("Invalid error");
            }
            this.isSuccess = isSuccess;
            this.error = error;
        }

        public boolean isSuccess() {
            return this.isSuccess;
        }

        public boolean isFailure() {
            return !this.isSuccess;
        }

        public Error getError() {
            return this.error;
        }

        public static Result success() {
            return new Result();
        }

        public static Result failure(Error error) {
            return new Result(false, error);
        }
    }
}
