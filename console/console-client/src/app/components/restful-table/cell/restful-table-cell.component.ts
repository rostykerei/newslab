import {AfterViewInit, Component, ComponentFactoryResolver, Input, ViewChild} from "@angular/core";
import {RestfulTableColumn} from "../types/restful-table-column.type";
import {RestfulTableCellDirective} from "./restful-table-cell.directive";
import {RestfulTableDefaultCellRenderer} from "./restful-table-default-cell-renderer.component";
import {RestfulTableCellRenderer} from "./restful-table-cell-renderer.type";

@Component({
  selector: 'cell',
  template: '<ng-template cell-host></ng-template>'
})
export class RestfulTableCellComponent implements AfterViewInit {

  @Input() column: RestfulTableColumn;
  @Input() row: Object;

  @ViewChild(RestfulTableCellDirective) cellHost: RestfulTableCellDirective;

  constructor(private componentFactoryResolver: ComponentFactoryResolver) {
  }

  ngAfterViewInit(): void {
    let componentRef = this.cellHost.viewContainerRef.createComponent(
      this.componentFactoryResolver.resolveComponentFactory(this.column.renderer || RestfulTableDefaultCellRenderer)
    );

    (<RestfulTableCellRenderer> componentRef.instance).row = this.row;
    (<RestfulTableCellRenderer> componentRef.instance).column = this.column;

    componentRef.changeDetectorRef.detectChanges();
  }
}
