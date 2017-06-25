import {Component} from "@angular/core";
import {Column} from "../../components/restful-table/column";

@Component({
  selector: 'nl-publishers-component',
  templateUrl: 'publishers.template.html'
})
export class PublishersComponent {

  tableColumns: Column[] = [
    { id: 'renderEngine', title: 'Rendering engile', sortable: true},
    { id: 'browser', title: 'Browser', sortable: true },
    { id: 'platforms', title: 'Platform(s)', sortable: true },
    { id: 'engineVersion', sortable: true},
    { id: '$actions', title: 'Actions', sortable: false }
  ];

}
