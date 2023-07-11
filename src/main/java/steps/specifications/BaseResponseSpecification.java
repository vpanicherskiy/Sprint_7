package steps.specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.parsing.Parser;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public class BaseResponseSpecification {
    public static final int SC_OK = HttpStatus.SC_OK;
    public static final int SC_CREATED = HttpStatus.SC_CREATED;
    public static final int SC_BAD_REQUEST = HttpStatus.SC_BAD_REQUEST;
    public static final int SC_CONFLICT = HttpStatus.SC_CONFLICT;
    public static final int SC_NOT_FOUND = HttpStatus.SC_NOT_FOUND;

    public static ResponseSpecification baseResponseSpecification(int statusCode, String contentType) {
        Parser defaultParser = Parser.JSON;
        LogDetail status = LogDetail.STATUS;

        return new ResponseSpecBuilder()
                .setDefaultParser(defaultParser)
                .expectStatusCode(statusCode)
                .expectContentType(contentType)
                .log(status)
                .build();
    }

}
