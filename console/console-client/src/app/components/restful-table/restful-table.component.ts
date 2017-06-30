import {Component, Input, OnInit} from "@angular/core";
import {RestfulTableService} from "./restful-table-data.service";
import {RestfulTableData} from "./types/restful-table-data.type";
import {RestfulTableColumn} from "./types/restful-table-column.type";
import {RestfulTableSort} from "./types/restful-table-sort.type";

@Component({
  selector: 'nl-restful-table',
  providers: [RestfulTableService],
  templateUrl: 'restful-table.template.html'
})
export class RestfulTableComponent implements OnInit {

  init: boolean = false;
  loading: boolean = false;

  @Input() title: String;
  @Input() columns: RestfulTableColumn[] = [];

  @Input() defaultPageSize?: 10 |25 | 50 | 100 | 200;
  @Input() defaultSort?: RestfulTableSort[];

  rows: Object[] = [];

  pageNumber: number = 1;
  pageSize: number = 25;

  totalPages: number = 0;
  totalElements: number = 0;

  sort: RestfulTableSort[] = [];

  pagination: number[] = [];

  ROWS_PER_PAGE_MENU: number[] = [10, 25, 50, 100, 200];

  constructor(private tableDataService: RestfulTableService) {

  }

  ngOnInit(): void {
    this.loadData(1, this.defaultPageSize || this.pageSize, this.defaultSort || this.sort);
  }

  loadData(pageNumber: number = this.pageNumber, pageSize: number = this.pageSize, sort: RestfulTableSort[] = this.sort): void {
    this.loading = true;

    this.tableDataService.getData(pageNumber, pageSize, sort).then(
      data => this.dataLoaded(data)
    );
  }

  dataLoaded(data:RestfulTableData): void {
    this.tableData = data;
    this.init = true;
    this.loading = false;
  }

  set tableData(tableData: RestfulTableData) {
    this.rows = tableData.data;

    this.sort = tableData.sort;
    this.pageNumber = tableData.pageNumber;
    this.pageSize = tableData.pageSize;
    this.totalElements = tableData.totalElements;
    this.totalPages = tableData.pageSize > 0 ? Math.ceil(tableData.totalElements / tableData.pageSize) : 0;

    let paginationFrom = tableData.pageNumber < 3 ? 1 : tableData.pageNumber - 2;
    let paginationTo = paginationFrom + 4;

    if (paginationTo > this.totalPages) {
      paginationTo = this.totalPages;
      paginationFrom = paginationTo < 5 ? 1 : paginationTo - 4;
    }

    this.pagination = [];
    for (let p = paginationFrom; p <= paginationTo; p++) {
      this.pagination.push(p);
    }
  }

  getColumnSort(column: RestfulTableColumn): 'ASC' | 'DESC' | 'NONE' {
    for (let sort of this.sort){
      if (sort.column == column.id) {
        return sort.direction;
      }
    }

    return 'NONE';
  }

  changeSort(column: RestfulTableColumn): void {
    if (column.sortable) {
      this.loadData(1, this.pageSize, [
        {
          column: column.id,
          direction: this.getColumnSort(column) == 'ASC' ? 'DESC' : 'ASC'
        }
      ]);
    }
  }

  refreshData(e: Event): void {
    e.preventDefault();
    this.loadData();
  }

  changePage(page: number): void {
    this.loadData(page);
  }

  changePageSize(n: number): void {
    this.loadData(1, n);
  }

}
