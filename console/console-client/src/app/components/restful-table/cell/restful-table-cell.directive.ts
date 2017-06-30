import {Directive, ViewContainerRef} from "@angular/core";

@Directive({
  selector: '[cell-host]',
})
export class RestfulTableCellDirective {
  constructor(public viewContainerRef: ViewContainerRef) { }
}
