package com.project.haratres.dto;

import lombok.Data;

import java.util.List;

@Data
public class InboundProductDto {
    String idocNo;
    List<StyleProductDto> products;

    /*
    {
    "Products[
        {
            code="M400502087-0002",
            name = "Kırmızı elibse",
            description = "elbise çok hoş",
            color:"kırmızı",
            gender:"kadın",
            variants:
            [
            {
            code:"M400502087-0002-36",
            stock:"10",
            size:"36",
            price:"2500"
            },
            code:"M400502087-0002-38",
            stock:"10",
            size:"38",
            price:"2500"
            },
            code:"M400502087-0002-40",
            stock:"10",
            size:"40",
            price:"2500"
            },
            code:"M400502087-0002-42",
            stock:"10",
            size:"40",
            price:"2500"

            }
            ]
        },
        {
            code="M400502087-0003",
            name = "Kırmızı elibse",
            description = "elbise çok hoş",
            color:"kırmızı",
            gender:"kadın",
            variants:
            [
            {
            code:"M400502087-0003-43",
            stock:"10",
            size:"36",
            price:"2500"
            },
            code:"M400502087-0003-23",
            stock:"10",
            size:"38",
            price:"2500"
            },
            code:"M400502087-0003-32",
            stock:"10",
            size:"40",
            price:"2500"
            },
            code:"M400502087-0003-42",
            stock:"10",
            size:"40",
            price:"2500"
            }
        }
    ]
}
     */
}
