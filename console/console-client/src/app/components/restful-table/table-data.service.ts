import {Injectable} from "@angular/core";
import {Sort, TableData} from "./table-data";
import {Http} from "@angular/http";
import "rxjs/add/operator/toPromise";

@Injectable()
export class TableDataService {

  constructor(private http: Http) { }

  getData(pageNumber: number, pageSize: number, sort: Sort[]): Promise<TableData>  {

    let url: string = `http://localhost:8000/publisher?pageNumber=${pageNumber}&pageSize=${pageSize}`;

    if (sort.length > 0) {
      for(let s of sort) {
        url += `&sort=${s.column},${s.direction}`
      }
    }

    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as TableData)
      .catch(this.handleError);

/*    return new Promise(resolve => {
      // Simulate server latency with 1 second delay
      setTimeout(() => resolve(td), 1000);
    });*/

  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
