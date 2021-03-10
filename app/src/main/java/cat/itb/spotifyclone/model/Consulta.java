
package cat.itb.spotifyclone.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Consulta {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("next")
    @Expose
    private String next;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}
