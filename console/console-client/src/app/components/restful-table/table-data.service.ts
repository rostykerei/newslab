import {Injectable} from "@angular/core";
import {TableData} from "./table-data";

@Injectable()
export class TableDataService {

  getData(): Promise<TableData>  {
    let td: TableData = {
      sortedByColumn: 'browser',
      sortingDirection: 'DESC',
      rowsTotal: 100,
      currentPage: 1,
      rowsPerPage: 25,
      data: [
        {
          renderEngine: 'Trident',
          browser: 'Internet Explorer',
          platforms: 'Windows',
          engineVersion: '5.2'
        },
        {
          renderEngine: 'Gecko',
          browser: 'Firefox 3.0',
          platforms: 'KDE 3.1',
          engineVersion: '4.7'
        },
        {
          renderEngine: 'Presto',
          browser: 'Netscape Navigator 69',
          platforms: 'Gnome',
          engineVersion: '3.8'
        },
        {
          renderEngine: 'KHTML',
          browser: 'Mozilla 1.8r',
          platforms: '	Embedded devices',
          engineVersion: '1.1'
        },
        {
          renderEngine: 'Misc',
          browser: 'NetFront 3.1',
          platforms: 'OSX.2+',
          engineVersion: '7.8'
        }
      ]
    };

    return new Promise(resolve => {
      // Simulate server latency with 1 second delay
      setTimeout(() => resolve(td), 1000);
    });

  }

}
