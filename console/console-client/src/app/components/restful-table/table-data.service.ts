import {Injectable} from "@angular/core";
import {TableData} from "./table-data";
import {Http} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {Sort} from "./sort";

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
      .catch(error => Promise.reject(error));
  }

}
