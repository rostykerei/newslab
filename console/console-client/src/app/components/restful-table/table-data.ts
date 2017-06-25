export interface TableData {

  sortedByColumn: string,
  sortingDirection: string,

  rowsTotal: number,

  currentPage: number,
  rowsPerPage: number,

  data: any[]

}
