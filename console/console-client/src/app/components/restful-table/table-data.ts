export enum SortDirection {
  ASC, DESC
}

export interface Sort {
  column: string,
  direction: 'ASC' | 'DESC'
}

export interface TableData {

  pageSize: number,
  pageNumber: number,

  totalElements: number,

  sort: Sort[],

  data: Object[]

}
