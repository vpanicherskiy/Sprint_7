package data.request.order;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GetOrdersRequest {
    private int courierId;
    private String nearestStation;
    private int limit;
    private int page;
}
