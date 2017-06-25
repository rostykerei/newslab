import {Component} from "@angular/core";
import {ColumnDefinition} from "../../components/restful-table/column-definition";

@Component({
  selector: 'nl-publishers-component',
  templateUrl: 'publishers.template.html'
})
export class PublishersComponent {

  tableColumns: ColumnDefinition[] = [
    { name: 'renderEngine', title: 'Rendering engile', sortable: true },
    { name: 'browser', title: 'Browser', sortable: true },
    { name: 'platforms', title: 'Platform(s)', sortable: true },
    { name: 'engineVersion', sortable: true},
    { name: 'actions', title: 'Actions', sortable: false }
  ];

}
