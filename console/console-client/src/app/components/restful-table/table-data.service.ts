import {Injectable} from "@angular/core";
import {TableData} from "./table-data";

@Injectable()
export class TableDataService {

  getData(): Promise<TableData>  {
    let td: TableData = {
      "pageSize":10,
      "pageNumber": 2,
      "totalElements":22,
      "sort":[
        {
          "column":"title",
          "direction": "DESC"
        },
        {
          "column":"id",
          "direction": "ASC"
        }
      ],
      "data":[
        {
          "id":461,
          "canonicalName":"test-publisher-460.com",
          "title":"test-publisher-460",
          "url":"http://www.test-publisher-460.com",
          "description":"Test Publisher 460",
          "active":true
        },
        {
          "id":460,
          "canonicalName":"test-publisher-459.com",
          "title":"test-publisher-459",
          "url":"http://www.test-publisher-459.com",
          "description":"Test Publisher 459",
          "active":true
        },
        {
          "id":459,
          "canonicalName":"test-publisher-458.com",
          "title":"test-publisher-458",
          "url":"http://www.test-publisher-458.com",
          "description":"Test Publisher 458",
          "active":true
        },
        {
          "id":458,
          "canonicalName":"test-publisher-457.com",
          "title":"test-publisher-457",
          "url":"http://www.test-publisher-457.com",
          "description":"Test Publisher 457",
          "active":true
        },
        {
          "id":457,
          "canonicalName":"test-publisher-456.com",
          "title":"test-publisher-456",
          "url":"http://www.test-publisher-456.com",
          "description":"Test Publisher 456",
          "active":true
        },
        {
          "id":456,
          "canonicalName":"test-publisher-455.com",
          "title":"test-publisher-455",
          "url":"http://www.test-publisher-455.com",
          "description":"Test Publisher 455",
          "active":true
        },
        {
          "id":455,
          "canonicalName":"test-publisher-454.com",
          "title":"test-publisher-454",
          "url":"http://www.test-publisher-454.com",
          "description":"Test Publisher 454",
          "active":true
        },
        {
          "id":454,
          "canonicalName":"test-publisher-453.com",
          "title":"test-publisher-453",
          "url":"http://www.test-publisher-453.com",
          "description":"Test Publisher 453",
          "active":true
        },
        {
          "id":453,
          "canonicalName":"test-publisher-452.com",
          "title":"test-publisher-452",
          "url":"http://www.test-publisher-452.com",
          "description":"Test Publisher 452",
          "active":true
        },
        {
          "id":452,
          "canonicalName":"test-publisher-451.com",
          "title":"test-publisher-451",
          "url":"http://www.test-publisher-451.com",
          "description":"Test Publisher 451",
          "active":true
        }
      ]
    };

    return new Promise(resolve => {
      // Simulate server latency with 1 second delay
      setTimeout(() => resolve(td), 1000);
    });

  }

}
