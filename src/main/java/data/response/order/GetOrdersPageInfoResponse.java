package data.response.order;

import lombok.Getter;

@Getter
public class GetOrdersPageInfoResponse {
    private int page;
    private int total;
    private int limit;
}
