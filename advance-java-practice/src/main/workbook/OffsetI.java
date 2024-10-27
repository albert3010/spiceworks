public interface OffsetI {

    int getOffset(String consumerId);
    void updateOffset(String consumerId, int offset) throws Exception;
}
