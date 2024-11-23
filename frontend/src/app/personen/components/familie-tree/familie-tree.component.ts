import {Component, OnInit} from '@angular/core';
import {NgxEchartsDirective, provideEcharts} from "ngx-echarts";
import {CommonModule} from "@angular/common";
import {EChartsOption} from "echarts";
import {Store} from "@ngrx/store";
import {PersonenState} from "../../store/personen.reducer";
import {getNakomelingenVan, getVooroudersVan} from "../../store/personen.selector";
import {laadNatuurlijkPersoonFiche} from "../../store/personen.acties";

@Component({
  selector: 'app-familie-tree',
  standalone: true,
  imports: [CommonModule, NgxEchartsDirective],
  templateUrl: './familie-tree.component.html',
  styleUrl: './familie-tree.component.scss',
  providers: [
    provideEcharts(),
  ]
})
export class FamilieTreeComponent implements OnInit {

  optionsVoorouders: EChartsOption | null = null;
  optionsNakomelingen: EChartsOption | null = null;

  constructor(private store: Store<PersonenState>) {
    this.store.select(getVooroudersVan).subscribe(voorouder => {
      let data : any[] = [voorouder];

      this.optionsVoorouders =  {
        tooltip: {
          trigger: 'item',
          triggerOn: 'mousemove'
        },
        series: [
          {
            type: 'tree',
            data: data,
            top: '1%',
            left: '15%',
            bottom: '1%',
            right: '7%',
            symbolSize: 7,
            orient: 'RL',
            label: {
              position: 'right',
              verticalAlign: 'middle',
              align: 'left'
            },
            leaves: {
              label: {
                position: 'left',
                verticalAlign: 'middle',
                align: 'right'
              }
            },
            emphasis: {
              focus: 'descendant'
            },
            expandAndCollapse: false,
            animationDuration: 550,
            animationDurationUpdate: 750
          }
        ]
      }

    });


    this.store.select(getNakomelingenVan).subscribe(nakomelingen => {
      let data : any[] = [nakomelingen];

      this.optionsNakomelingen =  {
        tooltip: {
          trigger: 'item',
          triggerOn: 'mousemove'
        },
        series: [
          {
            type: 'tree',
            data: data,
            top: '1%',
            left: '7%',
            bottom: '1%',
            right: '20%',
            symbolSize: 7,
            label: {
              position: 'left',
              verticalAlign: 'middle',
              align: 'right',
              fontSize: 9
            },
            leaves: {
              label: {
                position: 'right',
                verticalAlign: 'middle',
                align: 'left'
              }
            },
            emphasis: {
              focus: 'descendant'
            },
            expandAndCollapse: false,
            animationDuration: 550,
            animationDurationUpdate: 750
          }
        ]
      }

    })
  }


  ngOnInit(): void {




  }

  clickNode($event: any) {
    let id: number = $event.value[0];
    this.store.dispatch(laadNatuurlijkPersoonFiche({natuurlijkPersoonId:id}));
  }
}
