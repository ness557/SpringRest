package ness.repository;

public interface SequenceRepository {

    int getNextSeqId(String key);
}
