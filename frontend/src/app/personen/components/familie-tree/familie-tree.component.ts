import {Component, OnInit} from '@angular/core';
import {NgxEchartsDirective, provideEcharts} from "ngx-echarts";
import {CommonModule} from "@angular/common";
import {EChartsOption} from "echarts";

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

  options: EChartsOption | null = null;



  ngOnInit(): void {
    this.options =  {
      tooltip: {
        trigger: 'item',
        triggerOn: 'mousemove'
      },
      series: [
        {
          type: 'tree',
          data: [{
            "name": "Stijn ",
            "children": [
              {
                "name": "Jana Coene",

              },
              {
                "name": "Emma Coene",

              },
              {
                "name": "Tibo Coene",

              },
             ]
          }
          ],
          left: '2%',
          right: '2%',
          top: '8%',
          bottom: '20%',
          symbol: 'emptyCircle',
          orient: 'vertical',
          expandAndCollapse: true,
          label: {
            position: 'top',
            rotate: -90,
            verticalAlign: 'middle',
            align: 'right',
            fontSize: 12
          },
          leaves: {
            label: {
              position: 'bottom',
              rotate: -90,
              verticalAlign: 'middle',
              align: 'left'
            }
          },
          animationDurationUpdate: 750
        }
      ]
    }



    /*const xAxisData = [];
    const data1 = [];
    const data2 = [];

    for (let i = 0; i < 100; i++) {
      xAxisData.push('category' + i);
      data1.push((Math.sin(i / 5) * (i / 5 - 10) + i / 6) * 5);
      data2.push((Math.cos(i / 5) * (i / 5 - 10) + i / 6) * 5);
    }

    this.options = {
      legend: {
        data: ['bar', 'bar2'],
        align: 'left',
      },
      tooltip: {},
      xAxis: {
        data: xAxisData,
        silent: false,
        splitLine: {
          show: false,
        },
      },
      yAxis: {},
      series: [
        {
          name: 'bar',
          type: 'bar',
          data: data1,
          animationDelay: idx => idx * 10,
        },
        {
          name: 'bar2',
          type: 'bar',
          data: data2,
          animationDelay: idx => idx * 10 + 100,
        },
      ],
      animationEasing: 'elasticOut',
      animationDelayUpdate: idx => idx * 5,
    };*/
  }

}
