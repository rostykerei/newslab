export interface Column {
  id: string,
  title?: string,

  sortable?: boolean,
  sortDir?: 'ASC' | 'DESC'
}
