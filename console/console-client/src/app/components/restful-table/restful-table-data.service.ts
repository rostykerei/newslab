import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {RestfulTableData} from "./types/restful-table-data.type";
import {RestfulTableSort} from "./types/restful-table-sort.type";

@Injectable()
export class RestfulTableService {

  constructor(private http: Http) { }

  getData(pageNumber: number, pageSize: number, sort: RestfulTableSort[]): Promise<RestfulTableData>  {

    let url: string = `http://localhost:8000/publisher?pageNumber=${pageNumber}&pageSize=${pageSize}`;

    if (sort.length > 0) {
      for(let s of sort) {
        url += `&sort=${s.column},${s.direction}`
      }
    }

    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as RestfulTableData)
      .catch(error => Promise.reject(error));
  }

}
