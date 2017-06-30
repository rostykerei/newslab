import {RestfulTableSort} from "./restful-table-sort.type";

export interface RestfulTableData {
  pageSize: number,
  pageNumber: number,

  totalElements: number,

  sort: RestfulTableSort[],

  data: Object[]
}
