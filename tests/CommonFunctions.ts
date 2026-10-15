import { Document, HeadingLevel, Packer, Paragraph, ImageRun } from 'docx';
import fs from 'fs';

let stepnumber = 0;

const screenshots: {
    step: number;
    time: string;
    path: string;
}[] = [];

function getTimestamp(): string {
    const now = new Date();

    return (
        now.getFullYear() +
        '-' +
        (now.getMonth() + 1).toString().padStart(2, '0') +
        '-' +
        now.getDate().toString().padStart(2, '0') +
        '_' +
        now.getHours().toString().padStart(2, '0') +
        '-' +
        now.getMinutes().toString().padStart(2, '0') +
        '-' +
        now.getSeconds().toString().padStart(2, '0')
    );
}

export async function takeScreenshot(page: any) {

    stepnumber++;

    const time = getTimestamp();

    const screenshotpath =
        'screenshots/Step' + stepnumber + '-' + time + '.png';

    await page.screenshot({
        path: screenshotpath
    });

    screenshots.push({
        step: stepnumber,
        time: time,
        path: screenshotpath
    });
}

export async function createWordReport(testInfo: any) {

    const children: Paragraph[] = [];

    children.push(
        new Paragraph({
            text: 'Playwright Execution Report',
            heading: HeadingLevel.TITLE
        })
    );

    for (const screenshot of screenshots) {

        children.push(
            new Paragraph({
                text: 'Step ' + screenshot.step + ' - ' + screenshot.time,
                heading: HeadingLevel.HEADING_2
            })
        );

        const image = fs.readFileSync(screenshot.path);

        children.push(
            new Paragraph({
                children: [
                    new ImageRun({
                        type: 'png',
                        data: image,
                        transformation: {
                            width: 700,
                            height: 400
                        }
                    })
                ]
            })
        );
    }

    const doc = new Document({
        sections: [
            {
                children: children
            }
        ]
    });

    const buffer = await Packer.toBuffer(doc);

    const testName = testInfo.title.replace(
        /[^a-zA-Z0-9-_]/g,
        '_'
    );

    const fileName = `Execution Report/${testName}_${getTimestamp()}.docx`;

    fs.writeFileSync(fileName, buffer);

    console.log(`Word report created: ${fileName}`);
}