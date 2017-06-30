import {Sort} from "./sort";

export interface TableData {

  pageSize: number,
  pageNumber: number,

  totalElements: number,

  sort: Sort[],

  data: Object[]

}
