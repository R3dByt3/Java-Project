package com.rest.choice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VotingDtoRepository extends MongoRepository<VotingDto, String> {
}
