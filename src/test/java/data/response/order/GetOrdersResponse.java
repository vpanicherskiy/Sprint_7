package data.response.order;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class GetOrdersResponse {
    private ArrayList<GetOrdersOneOrderResponse> orders;
    private GetOrdersPageInfoResponse pageInfo;
    private ArrayList<GetOrderAvailableStationResponse> availableStations;
}
