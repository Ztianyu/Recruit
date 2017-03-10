package cn.zty.baselib.bean;

/**
 * 网络结果数据bean
 */
public class ResultBean<T> {
    private T data;
    private ResultHeader head;

    public T getResult() {
        return data;
    }

    public void setResult(T result) {
        this.data = result;
    }

    public ResultHeader getHead() {
        return head;
    }

    public void setHead(ResultHeader head) {
        this.head = head;
    }
}
